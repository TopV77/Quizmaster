import {CategoryShort} from "./categoryShort";

export interface QuizShort {
  id?: number;
  name: string;
  description?: string;
  isFavorite: boolean;
  category: CategoryShort;
  questionSize: number;
}
