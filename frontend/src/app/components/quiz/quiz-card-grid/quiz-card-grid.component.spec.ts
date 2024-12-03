import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuizCardGridComponent } from './quiz-card-grid.component';

describe('QuizCardLayoutComponent', () => {
  let component: QuizCardGridComponent;
  let fixture: ComponentFixture<QuizCardGridComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [QuizCardGridComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(QuizCardGridComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
