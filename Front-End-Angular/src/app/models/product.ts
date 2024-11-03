export interface Product {
  id: number;
  imageUrl: string;
  name: string;
  description: string;
  price: number;
  category?: string;
  title: string;
  rating: number;




  reviewsCount: number;
  availability: boolean;
  additionalInfo: string;
}
