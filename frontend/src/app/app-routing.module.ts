import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from './pages/home/home.component';
import {CategoriesComponent} from './pages/categories/categories.component';
import {QuizzesComponent} from './pages/quizzes/quizzes.component';
import {QuestionsComponent} from './pages/questions/questions.component';
import {FavoritesComponent} from './pages/favorites/favorites.component';
import {ErrorComponent} from "./pages/error/error.component";
import {QuestionDetailsComponent} from "./pages/question-details/question-details.component";
import {QuestionFormComponent} from "./components/question/question-form/question-form.component";
import {CategoryFormComponent} from "./components/category/category-form/category-form.component";
import {QuizFormComponent} from "./components/quiz/quiz-form/quiz-form.component";
import {TagsComponent} from "./pages/tags/tags.component";
import {PasswordComponent} from './components/password/password.component';
import {authGuard} from "./guards/authGuard/auth.guard";
import {GameComponent} from "./pages/game/game.component";
import {QuizDetailsComponent} from "./pages/quiz-details/quiz-details.component";
import {CategoryDetailsComponent} from "./pages/category-details/category-details.component";
import {TagDetailsComponent} from "./pages/tag-details/tag-details.component";
import {TagFormComponent} from "./components/tag/tag-form/tag-form.component";


const routes: Routes = [
  {path: '', redirectTo: 'home', pathMatch: 'full'},
  {path: 'home', component: HomeComponent},
  {path: 'categories', component: CategoriesComponent},
  {path: 'category/create', component: CategoryFormComponent},
  {path: 'category/:id', component: CategoryDetailsComponent},
  {path: 'category/edit/:id', component: CategoryFormComponent, canActivate: [authGuard]},
  {path: 'quizzes', component: QuizzesComponent},
  {path: 'quiz/create', component: QuizFormComponent},
  {path: 'quiz/:id', component: QuizDetailsComponent},
  {path: 'quiz/edit/:id', component: QuizFormComponent, canActivate: [authGuard]},
  {path: 'tags', component: TagsComponent},
  {path: 'tag/create', component: TagFormComponent},
  {path: 'tag/:id', component: TagDetailsComponent},
  {path: 'tag/edit/:id', component: TagFormComponent},
  {path: 'questions', component: QuestionsComponent},
  {path: 'question/create', component: QuestionFormComponent},
  {path: 'question/:id', component: QuestionDetailsComponent},
  {path: 'question/edit/:id', component: QuestionFormComponent, canActivate: [authGuard]},
  {path: 'favorites', component: FavoritesComponent},
  {path: 'authorize', component: PasswordComponent},
  {path: 'game', component: GameComponent},
  {path: '**', component: ErrorComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
