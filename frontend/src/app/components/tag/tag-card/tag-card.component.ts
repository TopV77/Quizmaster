import {Component, Input} from '@angular/core';
import {TagShort} from "../../../interfaces/tagShort";

@Component({
  selector: 'app-tag-card',
  templateUrl: './tag-card.component.html',
  styleUrl: './tag-card.component.css'
})
export class TagCardComponent {
  @Input() tag?: TagShort;
}
