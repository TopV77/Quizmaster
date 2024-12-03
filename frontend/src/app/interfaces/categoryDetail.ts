import {QuizShort} from "./quizShort";

export interface CategoryDetail {
  id?: number;
  name: string;
  description?: string;
  password?: string;
  color?: string;
  quizzes?: QuizShort[];
}
