import {Component} from '@angular/core';
import {NavigationService} from "./services/navigation/navigation.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {

  constructor(private navigationService: NavigationService) {
  }
}
