import {QuizShort} from "./quizShort";
import {TagShort} from "./tagShort";

export interface QuestionDetail {
  id?: number;
  question: string;
  password?: string;
  questionType: string;
  correctAnswer: string;
  answerA?: string;
  answerB?: string;
  answerC?: string;
  answerD?: string;
  isFavorite: boolean;
  quizzes: QuizShort[];
  tags: TagShort[];
}
