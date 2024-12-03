import {CategoryShort} from "./categoryShort";
import {QuestionShort} from "./questionShort";

export interface QuizDetail {
  id?: number;
  name: string;
  description: string;
  password?: string;
  isFavorite: boolean;
  category: CategoryShort;
  questions: QuestionShort[];
}
