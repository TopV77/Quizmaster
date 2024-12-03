import {Component} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {TagService} from "../../../services/tag/tag.service";

@Component({
  selector: 'app-tag-form',
  templateUrl: './tag-form.component.html',
  styleUrl: './tag-form.component.css'
})
export class TagFormComponent {
  form: FormGroup;

  // id from URL, is 0 when no id found
  id: number = Number(this.activatedRoute.snapshot.paramMap.get('id'));
  hasNoQuestions: boolean = true;

  constructor(
    private fb: FormBuilder,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private tagService: TagService
  ) {
    this.form = this.fb.group({
      name: [null, Validators.required],
    });

    this.loadExistingTag();
  }

  private loadExistingTag() {
    if (this.id > 0) {
      this.tagService.getTagById(this.id).subscribe(data => {

        if (data) {
          this.hasNoQuestions = data.questions.length == 0;
          this.form.patchValue({name: data.name});
        }
      });
    }
  }

  onSubmit(): void {
    if (this.form.valid) {
      let formData = this.form.value;

      if (this.id > 0) {
        formData.id = this.id;
        this.tagService.updateTag(formData).subscribe({
          next: () => this.router.navigateByUrl('/tags'),
          error: err => console.error('Loading category failure:', err)
        });
      } else {
        this.tagService.addTag(formData).subscribe({
          next: () => this.router.navigateByUrl('/tags'),
          error: err => console.error('Creating category failure:', err)
        });
      }

    }
  }

}
