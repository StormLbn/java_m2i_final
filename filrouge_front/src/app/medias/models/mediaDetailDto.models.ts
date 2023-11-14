// media-detail-dto.model.ts

import { MediaType } from '../enums/media-type';
import { ProfessionalDTO } from "./professionalDto.models"; // Adjust the import path

export class MediaDetailDTO {
  constructor(
    public id: string,
    public title: string,
    public type: MediaType,
    public plot: string,
    public imageUrl: string,
    public releaseYear: number,
    public duration: number,
    public avgRating: number | null,
    public genres: string[],
    public seasons: number,
    public inProduction: boolean,
    public actors: ProfessionalDTO[],
    public producers: ProfessionalDTO[],
    public writers: ProfessionalDTO[],
    public directors: ProfessionalDTO[],
  ) {}
}

export default MediaDetailDTO;

