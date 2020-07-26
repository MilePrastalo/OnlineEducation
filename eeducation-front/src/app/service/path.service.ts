import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PathService {

  public path = 'http://localhost:8080/'

  constructor() {
  }
}
