import {CanActivateFn, Router} from '@angular/router';
import {inject} from '@angular/core';
import {PasswordService} from "../../services/password/password.service";
import {map, Observable, of} from 'rxjs';

// Generic verification function
function verifyToken(
  id: number,
  mode: string,
  verifyMethod: (id: number, payload: { token: string }) => Observable<boolean>,
  router: Router
): Observable<boolean> {
  const token = sessionStorage.getItem(`${mode}Authorized_${id}`);

  // Handle invalid or missing ID
  if (!id || isNaN(id)) {
    router.navigateByUrl('/');
    return of(false);
  }

  // If token exists, verify it
  if (token) {
    return verifyMethod(id, {token}).pipe(
      map(response => {
        if (response) {
          return true;
        } else {
          // Redirect to the auth page if token is wrong
          router.navigate(['/authorize'], {queryParams: {mode, id}});
          return false;
        }
      })
    );
  }

  // Redirect to the auth page if no token exists
  router.navigate(['/authorize'], {queryParams: {mode, id}});
  return of(false);
}


export const authGuard: CanActivateFn = (route, state): Observable<boolean> => {
  const router = inject(Router);
  const pwService = inject(PasswordService);

  // Extract 'id' and determine the mode based on URL segments
  const id = Number(route.params['id']);
  const urlPath = route.routeConfig?.path; // Get the URL path (e.g., 'category/:id')

  // Determine mode based on the first segment of the URL
  const mode = urlPath?.split('/')[0]; // Extract 'category', 'quiz', or 'question'

  // Map mode to the corresponding verification method
  const modeVerificationMap: Record<string, (id: number, payload: { token: string }) => Observable<boolean>> = {
    'category': pwService.verifyCategoryToken.bind(pwService),
    'quiz': pwService.verifyQuizToken.bind(pwService),
    'question': pwService.verifyQuestionToken.bind(pwService),
  };

  const verifyMethod = modeVerificationMap[mode || ''];

  if (verifyMethod) {
    return verifyToken(id, mode!, verifyMethod, router);
  } else {
    // Invalid mode, redirect to an error page or home
    router.navigateByUrl('/');
    return of(false);
  }
};
