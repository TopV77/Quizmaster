import {Component} from '@angular/core';
import {PasswordService} from '../../services/password/password.service';
import {ActivatedRoute, Router} from '@angular/router';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {AuthResponse} from "../../interfaces/AuthResponse";

@Component({
  selector: 'app-password',
  templateUrl: './password.component.html',
  styleUrl: './password.component.css'
})
export class PasswordComponent {

  form: FormGroup;
  mode: string | null;
  id: number;
  error?: boolean = false;

  constructor(
    private passwordService: PasswordService,
    private router: Router,
    private activatedRoute: ActivatedRoute) {

    // Get data from URL
    this.id = Number(this.activatedRoute.snapshot.queryParamMap.get('id'));
    this.mode = this.activatedRoute.snapshot.queryParamMap.get('mode');

    // Form
    this.form = new FormGroup({
      password: new FormControl('', [Validators.required, Validators.minLength(6)])
    })

  }

  onSubmit() {
    if (this.form.valid) {
      this.authorize(this.form.value);
    }
  }

  authorize(pw: object) {
    if (this.mode === "category") {
      this.passwordService.verifyCategoryPassword(this.id, pw).subscribe((response: AuthResponse) => {
        if (response && response.authToken) {
          // Set the authToken in sessionStorage
          sessionStorage.setItem(`categoryAuthorized_${this.id}`, response.authToken);

          // Navigate to the next route
          this.router.navigate(['/category/edit', this.id]);
        } else {
          this.error = true;  // Handle case when the response is null or authToken is missing
        }
      });
    }
    else if (this.mode === "quiz") {
      this.passwordService.verifyQuizPassword(this.id, pw).subscribe((response: AuthResponse) => {
        if (response && response.authToken) {
          // Set the authToken in sessionStorage
          sessionStorage.setItem(`quizAuthorized_${this.id}`, response.authToken);

          // Navigate to the next route
          this.router.navigate(['/quiz/edit', this.id]);
        } else {
          this.error = true;  // Handle case when the response is null or authToken is missing
        }
      });
    }
    else if (this.mode === "question") {
      this.passwordService.verifyQuestionPassword(this.id, pw).subscribe((response: AuthResponse) => {
        if (response && response.authToken) {
          // Set the authToken in sessionStorage
          sessionStorage.setItem(`questionAuthorized_${this.id}`, response.authToken);

          // Navigate to the next route
          this.router.navigate(['/question/edit', this.id]);
        } else {
          this.error = true;  // Handle case when the response is null or authToken is missing
        }
      });
    }
  }
}
