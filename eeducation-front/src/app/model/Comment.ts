export class Comment {
  constructor(public id: number,
              public user: string,
              public datePosted: Date,
              public replies: Array<Comment>) {
  }
}
