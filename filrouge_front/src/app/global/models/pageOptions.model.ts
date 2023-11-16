import { MediaType } from "src/app/medias/models/MediaDetail.models";

export interface pageOptions {
    pageNumber?: number;
    mediaType?: MediaType | null;
    filter?: string | null;
    search?: string | null;
}
