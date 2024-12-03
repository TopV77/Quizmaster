import {TagShort} from "./tagShort";


export interface QuestionShort {
  id?: number;
  question: string;
  questionType: string;
  isFavorite: boolean;
  tags: TagShort[];
}
