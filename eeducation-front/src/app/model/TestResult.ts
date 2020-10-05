import {UserQuestionResult} from './UserQuestionResult';

export class TestResult {
  public testId: number;
  public date: Date;
  public studentId: number;
  public userQuestionResults: Array<UserQuestionResult>;
  public score: string;

  constructor(testId: number) {
    this.testId = testId;
    this.userQuestionResults = [];
  }
}
