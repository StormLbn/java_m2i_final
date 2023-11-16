export interface PageResponse<T> {
    pageNumber: number;
    totalPages: number;
    totalElements: number;
    itemsPerPage?: number
    content: T[];
}