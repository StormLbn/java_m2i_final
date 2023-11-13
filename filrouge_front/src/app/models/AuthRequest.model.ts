export interface AuthRequest {
    id?: string;
    mail: string;
    password: string;
    username?: string;
    birthDate?: Date;
}