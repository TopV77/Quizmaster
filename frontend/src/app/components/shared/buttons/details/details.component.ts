import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrl: './details.component.css'
})
export class DetailsComponent {
  @Input() url: string = '';
  @Input() itemId: number = 0;
}
