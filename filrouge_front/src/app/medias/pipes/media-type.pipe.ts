import { Pipe, PipeTransform } from '@angular/core';
import { MediaType } from '../models/MediaDetail.models';

@Pipe({
  name: 'mediaType'
})
export class MediaTypePipe implements PipeTransform {

  transform(value: MediaType): string {
    switch (value) {
      case "MOVIE" :
        return "film";
      case "SHOW" :
        return "série";
      default :
      return "";
    }
  }

}
