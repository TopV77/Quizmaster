import {Component} from '@angular/core';
import {Location} from '@angular/common'
import {NavigationService} from "../../../../services/navigation/navigation.service";

@Component({
  selector: 'app-back',
  templateUrl: './back.component.html',
  styleUrl: './back.component.css'
})
export class BackComponent {
  constructor(private location: Location, private navigationService: NavigationService) {
  }

  back(): void {
    this.navigationService.back();
  }
}
