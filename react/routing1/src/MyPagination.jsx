import Pagination from "react-bootstrap/Pagination";


function MyPagination({startPage, endPage, page, hasNext, hasPrev,click}) {

    const pages = Array.from({length : endPage - startPage +1},(_,index) => startPage + index);

    console.log(page);

    return(
        <>

         <Pagination>
                <Pagination.Prev onClick ={()=>click(startPage-1)} disabled={!hasPrev}/>
            {
                pages.map(p=>{
                    return(
                        <Pagination.Item key={p} active ={page ===p} onClick ={()=>click(p)} >{p}</Pagination.Item>
                )
            })
            }
                <Pagination.Next onClick ={()=>click(endPage +1)} disabled={!hasNext}/>
        </Pagination>
        </>
    )
    
}

function Page({label, page,click,active}) {
    return(
        <li onClick={()=>click(page)} className={`${active ? "active":""}`}>
            <a href="#" onClick={e=>e.preventDefault()}>{label}</a>
        </li>
    )
    
}
export default MyPagination;