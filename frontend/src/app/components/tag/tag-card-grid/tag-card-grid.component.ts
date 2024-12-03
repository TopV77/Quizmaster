import {Component} from '@angular/core';
import {TagShort} from "../../../interfaces/tagShort";
import {TagService} from "../../../services/tag/tag.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-tag-card-grid',
  templateUrl: './tag-card-grid.component.html',
  styleUrl: './tag-card-grid.component.css'
})
export class TagCardGridComponent {
  tags: TagShort[] = []

  constructor(private tagService: TagService, private router: Router) {
    this.loadCategories();
  }

  loadCategories(): void {
    this.tagService.getAllTags().subscribe(data => {
      this.tags = data;
    });
  }
}
