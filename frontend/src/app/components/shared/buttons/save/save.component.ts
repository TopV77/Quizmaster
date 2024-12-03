import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-save',
  templateUrl: './save.component.html',
  styleUrl: './save.component.css'
})
export class SaveComponent {
  @Input() disabled: boolean = false;
}
