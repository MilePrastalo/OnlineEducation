import {Test} from './Test';
import {Student} from './Student';
import {UserQuestionResult} from './UserQuestionResult';

export class TestResultView {
  public id: number;
  public student: Student;
  public test: Test;
  public userQuestionResults: Array<UserQuestionResult>;
}
