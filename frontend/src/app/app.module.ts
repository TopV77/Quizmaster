import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {HttpClientModule} from "@angular/common/http";
import {NgOptimizedImage} from "@angular/common";
import {AppComponent} from './app.component';
import {HomeComponent} from './pages/home/home.component';
import {HeaderComponent} from './components/header/header.component';
import {FooterComponent} from './components/footer/footer.component';

import {CategoriesComponent} from './pages/categories/categories.component';
import {QuizzesComponent} from './pages/quizzes/quizzes.component';
import {QuestionsComponent} from './pages/questions/questions.component';
import {FavoritesComponent} from './pages/favorites/favorites.component';

import {QuestionDetailsComponent} from './pages/question-details/question-details.component';
import {QuestionFormComponent} from './components/question/question-form/question-form.component';
import {ErrorComponent} from './pages/error/error.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {CategoryFormComponent} from './components/category/category-form/category-form.component';
import {PasswordComponent} from './components/password/password.component';
import {FavoriteQuestionsComponent} from './components/favorites/favorite-questions/favorite-questions.component';
import {QuizFormComponent} from './components/quiz/quiz-form/quiz-form.component';
import {TagsComponent} from './pages/tags/tags.component';
import {NgMultiSelectDropDownModule} from "ng-multiselect-dropdown";
import {GameComponent} from './pages/game/game.component';
import {QuestionSingleChoiceComponent} from './components/game/question-single-choice/question-single-choice.component';
import {
  QuestionMultipleChoiceComponent
} from './components/game/question-multiple-choice/question-multiple-choice.component';
import {QuestionTextInputComponent} from './components/game/question-text-input/question-text-input.component';
import {QuizDetailsComponent} from './pages/quiz-details/quiz-details.component';
import {QuizCardComponent} from './components/quiz/quiz-card/quiz-card.component';
import {QuizCardGridComponent} from './components/quiz/quiz-card-grid/quiz-card-grid.component';
import {CategoryDetailsComponent} from './pages/category-details/category-details.component';
import {TagDetailsComponent} from './pages/tag-details/tag-details.component';
import {BackComponent} from './components/shared/buttons/back/back.component';
import {DeleteComponent} from './components/shared/buttons/delete/delete.component';
import {EditComponent} from './components/shared/buttons/edit/edit.component';
import {CreateComponent} from './components/shared/buttons/create/create.component';
import {AddCardComponent} from './components/shared/add-card/add-card.component';
import {SaveComponent} from './components/shared/buttons/save/save.component';
import {StartComponent} from './components/shared/buttons/start/start.component';
import {DetailsComponent} from './components/shared/buttons/details/details.component';
import {QuestionsTableComponent} from './components/question/questions-table/questions-table.component';
import {CategoryCardGridComponent} from './components/category/category-card-grid/category-card-grid.component';
import {CategoryCardComponent} from './components/category/category-card/category-card.component';
import {TagCardComponent} from './components/tag/tag-card/tag-card.component';
import {TagCardGridComponent} from './components/tag/tag-card-grid/tag-card-grid.component';
import {TagFormComponent} from './components/tag/tag-form/tag-form.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HeaderComponent,
    FooterComponent,
    CategoriesComponent,
    QuizzesComponent,
    QuestionsComponent,
    FavoritesComponent,
    QuestionDetailsComponent,
    QuestionFormComponent,
    ErrorComponent,
    CategoryFormComponent,
    PasswordComponent,
    FavoriteQuestionsComponent,
    QuizFormComponent,
    TagsComponent,
    QuestionFormComponent,
    GameComponent,
    QuestionSingleChoiceComponent,
    QuestionMultipleChoiceComponent,
    QuestionTextInputComponent,
    QuizDetailsComponent,
    QuizCardComponent,
    QuizCardGridComponent,
    CategoryDetailsComponent,
    TagDetailsComponent,
    BackComponent,
    DeleteComponent,
    EditComponent,
    CreateComponent,
    AddCardComponent,
    SaveComponent,
    StartComponent,
    DetailsComponent,
    QuestionsTableComponent,
    CategoryCardGridComponent,
    CategoryCardComponent,
    TagCardComponent,
    TagCardGridComponent,
    TagFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgOptimizedImage,
    FormsModule,
    ReactiveFormsModule,
    NgMultiSelectDropDownModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
