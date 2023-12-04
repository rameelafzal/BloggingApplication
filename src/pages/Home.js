import { useEffect } from "react";
import Base from "../components/Base";
import NewsFeed from "../components/NewsFeed";
import { Container } from "reactstrap";

const Home=()=>{
    useEffect(
        ()=>{

        },[]
    )
    return (
        <Base> 
        <Container className="mt-3">
        <NewsFeed>
            
            </NewsFeed>
        </Container>
      
    </Base>
       
    )
}
export default Home;