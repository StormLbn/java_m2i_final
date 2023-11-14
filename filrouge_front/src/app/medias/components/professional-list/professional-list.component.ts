
import { Component, Input } from '@angular/core';
import ProfessionalDTO from "../../models/professionalDto.models";

@Component({
  selector: 'app-professional-list',
  templateUrl: './professional-list.component.html',
  styleUrls: ['./professional-list.component.css']
})
export class ProfessionalListComponent {
  @Input() professionals: ProfessionalDTO[] | undefined;
}

