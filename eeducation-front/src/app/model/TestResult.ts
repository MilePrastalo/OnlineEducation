import {UserQuestionResult} from './UserQuestionResult';

export class TestResult {
  public testId: number;
  public date: Date;
  public studentId: number;
  public userQuestionResults: Array<UserQuestionResult>;

  constructor(testId: number) {
    this.testId = testId;
    this.userQuestionResults = [];
  }
}
