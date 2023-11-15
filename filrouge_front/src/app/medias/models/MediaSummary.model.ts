import { MediaType } from "./MediaDetail.models";

export class MediaSummary {
    constructor(
        public id: string,
        public title: string,
        public type: MediaType,
        public imageUrl: string,
        public releaseYear: number,
        public duration: number,
        public avgRating: number | null,
        public genres: string[]
    ) {}
}
