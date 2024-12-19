import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrl: './create.component.css'
})
export class CreateComponent {
  @Input() url: string = '';
  @Input() text: string = "Create";
  @Input() useIcon: boolean = true;
}
