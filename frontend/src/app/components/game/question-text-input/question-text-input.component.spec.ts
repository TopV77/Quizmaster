import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuestionTextInputComponent } from './question-text-input.component';

describe('QuestionTextInputComponent', () => {
  let component: QuestionTextInputComponent;
  let fixture: ComponentFixture<QuestionTextInputComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [QuestionTextInputComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(QuestionTextInputComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
