import {QuestionShort} from "./questionShort";

export interface TagDetail {
  id?: number;
  name: string;
  questions: QuestionShort[];
}
