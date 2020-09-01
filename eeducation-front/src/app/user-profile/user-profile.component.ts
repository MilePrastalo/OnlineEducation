import {Component, OnInit} from '@angular/core';
import {UserBasic} from '../model/UserBasic';
import {UserService} from '../service/user.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {

  user: UserBasic;

  constructor(private userService: UserService) {
  }

  ngOnInit(): void {
    this.userService.getUser().subscribe(
      response => {
        this.user = response;
      }
    );
  }

}
