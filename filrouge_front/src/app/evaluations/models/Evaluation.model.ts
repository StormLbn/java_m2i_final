export interface Evaluation {
    id?: string;
    mediaId: string;
    mediaTitle?: string;
    userId: string;
    userName?: string;
    rating: number | null;
    comment: string;
}