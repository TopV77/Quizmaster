import {Injectable} from '@angular/core';
import {NavigationEnd, Router} from '@angular/router';
import {Location} from '@angular/common';

@Injectable({
  providedIn: 'root',
})
export class NavigationService {
  private history: string[] = [];

  constructor(private router: Router, private location: Location) {
    // Listen for route changes and track history
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.history.push(event.urlAfterRedirects);
        if (this.history.length > 10) this.history.shift(); // Limit history size
      }
    });
  }

  back(): void {
    if (this.history.length <= 1) {
      this.router.navigateByUrl('/'); // Fallback to home if no history
      return;
    }

    // Remove current URL from stack
    this.history.pop();

    // Get last URL (last element in array)
    let lastUrl = this.history[this.history.length - 1];

    if (lastUrl) {
      if (lastUrl.includes('authorize')) {
        this.back(); // Skip 'authorize' page and go back another step
      } else {
        this.router.navigateByUrl(lastUrl); // Navigate to the previous URL
      }
    } else {
      this.router.navigateByUrl('/'); // Fallback if history is empty or invalid
    }
  }
}
