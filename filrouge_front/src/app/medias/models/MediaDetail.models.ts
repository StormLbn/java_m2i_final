import { Professional } from "./Professional.models";

type MediaType = "MOVIE" | "SHOW";

class MediaDetail {
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
    public actors: Professional[],
    public producers: Professional[],
    public writers: Professional[],
    public directors: Professional[],
  ) { }
}

export { MediaDetail, MediaType };

