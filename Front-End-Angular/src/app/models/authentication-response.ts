export interface AuthenticationResponse {
  id:number;
  username:string;
  success: boolean; // Trạng thái thành công của đăng nhập
  message: string; // Thông điệp trả về
  token?: string; // Token sẽ được trả về nếu đăng nhập thành công
}
