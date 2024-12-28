import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { ChangeDetectorRef, Component, OnInit, HostListener } from "@angular/core";
import { TagService } from "../../../services/tag/tag.service";
import { QuizService } from "../../../services/quiz/quiz.service";
import { TagShort } from "../../../interfaces/tagShort";
import { ActivatedRoute, Router } from "@angular/router";
import { QuestionService } from "../../../services/question/question.service";
import { QuestionDetail } from "../../../interfaces/questionDetail";
import { QuizNameAndId } from "../../../interfaces/quizNameAndId";
import { removeNullValues } from "../../../helpers/form-helpers";

@Component({
    selector: 'app-question-form',
    templateUrl: './question-form.component.html',
    styleUrls: ['./question-form.component.css']
})
export class QuestionFormComponent implements OnInit {

    form: FormGroup;
    showCorrectAnswer = true;
    showCorrectAnswerText = false;

    // id from URL, is 0 when no id found
    id: number = Number(this.activatedRoute.snapshot.paramMap.get('id'));

    // All existing tags and quizzes
    tagsList: TagShort[] = [];
    quizList: QuizNameAndId[] = [];

    dropdownSettings = {};

    constructor(
        private fb: FormBuilder,
        private tagService: TagService,
        private quizService: QuizService,
        private activatedRoute: ActivatedRoute,
        private questionService: QuestionService,
        private router: Router,
        private cdr: ChangeDetectorRef
    ) {
        this.form = this.fb.group({
            question: [null, Validators.required],
            password: [null, [Validators.required, Validators.minLength(6)]],
            questionType: ['Single Choice', Validators.required],
            correctAnswer: ['A', Validators.required],
            correctAnswerText: [null],
            answerA: [null, Validators.required],
            answerB: [null, Validators.required],
            answerC: [null, Validators.required],
            answerD: [null, Validators.required],
            isFavorite: [false],
            tags: [[]],
            quizzes: [[]]
        });


        this.loadOptions();
        this.loadExistingQuestion();
    }

    private loadOptions() {
        this.tagService.getAllTags().subscribe(data => {
            this.tagsList = data;
        });

        this.quizService.getAllQuizzes().subscribe(data => {
            this.quizList = data;
        });
    }

    private loadExistingQuestion() {
        if (this.id > 0) {
            this.questionService.getQuestionById(this.id).subscribe(data => {

                if (data) {
                    let dataForForm: QuestionDetail = data;

                    // Patch data that needs no logic
                    this.form.patchValue({
                        question: dataForForm.question,
                        questionType: dataForForm.questionType,
                        isFavorite: dataForForm.isFavorite,
                        quizzes: dataForForm.quizzes,
                        tags: dataForForm.tags,
                    });
                    this.cdr.detectChanges(); // Trigger change detection to make sure angular applies all changes.

                    // Patch data based on Question type
                    if (dataForForm.questionType === 'Single Choice' || dataForForm.questionType === 'Multiple Choice') {

                        this.form.patchValue({
                            answerA: dataForForm.answerA,
                            answerB: dataForForm.answerB,
                            answerC: dataForForm.answerC,
                            answerD: dataForForm.answerD,
                        });
                        this.cdr.detectChanges();

                        // Split the semicolon seperated string and make sure there are no blanks
                        const correctAnswers = dataForForm.correctAnswer.split(';').map(answer => answer.trim());
                        this.form.patchValue({ correctAnswer: correctAnswers });

                    } else {
                        this.form.patchValue({
                            correctAnswerText: dataForForm.correctAnswer,
                        })
                    }

                    this.cdr.detectChanges(); // Trigger change detection to make sure changes are reflected in the UI

                    // Remove validation from password -> filed hidden on edit
                    this.form.get('password')?.removeValidators(Validators.required);
                    this.form.get('password')?.updateValueAndValidity();
                }
            });
        }
    }

    ngOnInit() {
        this.updateDropdownSettings(window.innerWidth);

        // Show hide correct answer field
        this.form.get('questionType')?.valueChanges.subscribe(value => {
            switch (value) {
                case 'Single Choice': {
                    this.showCorrectAnswer = true;
                    this.showCorrectAnswerText = false;
                    this.setValidatorsMCSC();
                    this.form.get('correctAnswer')?.setValue(['A']);
                    break;
                }
                case 'Multiple Choice': {
                    this.showCorrectAnswer = true;
                    this.showCorrectAnswerText = false;
                    this.setValidatorsMCSC();
                    this.form.get('correctAnswer')?.setValue(['A']);
                    break;
                }
                case 'Text Input': {
                    this.showCorrectAnswer = false;
                    this.showCorrectAnswerText = true;
                    this.form.get('correctAnswerText')?.setValidators(Validators.required);

                    // We had a bug that question was not required anymore after switch to Text Input
                    this.form.get('question')?.setValidators(Validators.required);
                    this.form.get('question')?.updateValueAndValidity();

                    const removeList = ['correctAnswer', 'answerA', 'answerB', 'answerC', 'answerD'];
                    this.removeFieldRequiredValidator(removeList);
                    this.updateAllFormValidation();
                    break;
                }
            }
        });
    }

    onSubmit() {

        if (this.form.valid) {
            let formData = this.form.value

            // Check if 'correctAnswer' is an array
            if (Array.isArray(formData.correctAnswer)) {
                if (formData.correctAnswer.length === 1) {
                    // Convert to a single string if only one value
                    formData.correctAnswer = formData.correctAnswer[0];
                } else if (formData.correctAnswer.length > 1) {
                    // Convert to a comma-separated string if multiple values
                    formData.correctAnswer = formData.correctAnswer.join('; ');
                }
            }

            // Copy correctAnswerText to correctAnswer if question type is text
            if (formData.questionType === 'Text Input') {
                formData.correctAnswer = formData.correctAnswerText;
                formData.correctAnswerText = null;
            } else {
                formData.correctAnswerText = null;
            }

            // Remove null or undefined values from form data
            let cleanedForm = removeNullValues(formData);

            // Do the PUT / POST requests
            if (this.id > 0) {
                cleanedForm.id = this.id;
                this.questionService.updateQuestion(cleanedForm).subscribe({
                    next: (question) => this.router.navigate(['/question', question.id]),
                    error: err => console.error('Update question failed:', err)
                });
            } else {
                this.questionService.addQuestion(cleanedForm).subscribe({
                    next: () => this.router.navigateByUrl('/questions'),
                    error: err => console.error('Create question failed:', err)
                });
            }

        }
    }

    // Helper functions
    private setValidatorsMCSC() {
        this.form.get('correctAnswerText')?.removeValidators(Validators.required);

        const addList = ['correctAnswer', 'answerA', 'answerB', 'answerC', 'answerD'];
        this.addFieldRequiredValidator(addList);
        this.updateAllFormValidation();
    }

    private removeFieldRequiredValidator(list: string[]) {
        list.forEach(field => {
            this.form.get(field)?.removeValidators(Validators.required);
        });
    }

    private addFieldRequiredValidator(list: string[]) {
        list.forEach(field => {
            this.form.get(field)?.addValidators(Validators.required);
        });
    }

    private updateAllFormValidation() {
        Object.keys(this.form.controls).forEach(key => {
            this.form.get(key)?.updateValueAndValidity();
        });
    }

    @HostListener('window:resize', ['$event'])
    onResize(event: any) {
        this.updateDropdownSettings(event.target.innerWidth);
    }

    updateDropdownSettings(width: number) {
        this.dropdownSettings = {
            singleSelection: false,
            idField: 'id',
            textField: 'name',
            itemsShowLimit: width < 768 ? 2 : 3,
            allowSearchFilter: true
        };
    }
}
