import axios from "axios";
import ClientCard from "components/ClientCard";
import Pagination from "components/Pagination";
import { useEffect } from "react";
import { useState } from "react";
import { ClientPage } from "types/client";
import { BASE_URL } from "utils/requests";

function List() {

    const [pageNumber, setPageNumber] = useState(0);

    const [page, setPage] = useState<ClientPage>({
        content: [],
        last: true,
        totalPages: 0,
        totalElements: 0,
        size: 12,
        number: 0,
        first: true,
        numberOfElements: 0,
        empty: true
    });

    useEffect(() => {
        axios.get(`${BASE_URL}/clients?size=12&page=${pageNumber}&sort=name`)
            .then(response => {
                const data = response.data as ClientPage;
                setPage(data);
            });
    }, [pageNumber]);

    return (
        <>
            <Pagination />

            <div className="container">
                <div className="row">
                    {page.content.map(client => (
                        <div key={client.id} className="col-sm-6 col-lg-4 col-xl-3 mb-3">
                            <ClientCard client={client} />
                        </div>
                    )
                    )}
                </div>
            </div>
        </>
    );
}

export default List;