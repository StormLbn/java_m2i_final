export interface AuthRequest {
    mail: string;
    password: string;
    confirm?: string;
    username?: string;
    birthDate?: Date;
}