import { Rating } from "./app/Rating";

export interface Product{
    id: number;
    Name: string;
    Type: string
    Instructor: string;
    Room_Number: string;
    Available: boolean;
    Price: number;
    Ratings:Array<Rating>;
}