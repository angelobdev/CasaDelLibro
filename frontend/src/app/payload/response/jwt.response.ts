export interface JwtResponse {
  token: string,
  type: string,
  id: number,
  username: string,
  email: string,
  roles: Array<string>,
  expiration: Date,
}
