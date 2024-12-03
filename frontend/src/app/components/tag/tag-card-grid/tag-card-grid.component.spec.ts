import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TagCardGridComponent } from './tag-card-grid.component';

describe('TagCardGridComponent', () => {
  let component: TagCardGridComponent;
  let fixture: ComponentFixture<TagCardGridComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TagCardGridComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TagCardGridComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
