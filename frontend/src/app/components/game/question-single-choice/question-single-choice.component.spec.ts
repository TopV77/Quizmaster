import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuestionSingleChoiceComponent } from './question-single-choice.component';

describe('QuestionSingleChoiceComponent', () => {
  let component: QuestionSingleChoiceComponent;
  let fixture: ComponentFixture<QuestionSingleChoiceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [QuestionSingleChoiceComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(QuestionSingleChoiceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
