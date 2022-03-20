import FormCard from 'components/FormCard';
import { useParams } from 'react-router-dom';

function Form() {

    const params = useParams();

    return (
        <FormCard clientCpf={`${params.clientCpf}`}/>
    );
}

export default Form;