export type Client = {
    id: number;
    cpf: string;
    name: string;
    email: string;
    income: number;
}

export type ClientPage = {
    content: Client[];
    last: boolean;
    totalPages: number;
    totalElements: number;
    size: number;
    number: number;
    first: boolean;
    numberOfElements: number;
    empty: boolean;
}
