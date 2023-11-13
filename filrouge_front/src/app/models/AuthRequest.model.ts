export interface AuthRequest {
    mail: string;
    password: string;
    username?: string;
    birthDate?: Date;
}