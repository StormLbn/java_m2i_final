import { Pipe, PipeTransform } from '@angular/core';
import { MediaType } from '../enums/media-type';

@Pipe({
  name: 'mediaType'
})
export class MediaTypePipe implements PipeTransform {

  transform(value: MediaType): string {
    return value === MediaType.MOVIE ? "film" : "série";
  }

}
