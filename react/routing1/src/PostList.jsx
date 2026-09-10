import { useEffect, useState } from "react";
import MyPagination from "./MyPagination";
import Container from "react-bootstrap/esm/Container";
import Spinner from 'react-bootstrap/Spinner';
import Table from 'react-bootstrap/Table';

async function getPosts(data, setPm, setIsLoading) {
    try{
        const queryString ='?'+ new URLSearchParams(data).toString();
        const response = await fetch("/api/posts" + queryString);
        if(!response.ok){
            return;
        }
        const result = await response.json();
        setPm(result);
        setIsLoading(false);

    }catch(e){
        console.error(e);
    }
}


function PostList() {
    const [pm, setPm] =useState({});
    const [data, setData] =useState({
        type : 'all',
        keyword : '',
        page : 0,
        size : 3,
        sort : 'id,desc'
    });

    const [isLoading, setIsLoading] = useState(true);
    
    useEffect(()=>{
        getPosts(data, setPm, setIsLoading);
    },[data])

    const clickHandler = (page)=>{
        page = page-1;
        setData({...data,page});
    }


    // 부트에서 dto.PageResponse 클래스
    return(
        <Container>
            <h1>게시글</h1>

            {
                isLoading ?
                <Spinner animation="border" role="status">
                    <span className="visually-hidden">Loading...</span>
                </Spinner> :
                <>
                <PostTable pm={pm} />
    
                <MyPagination 
                startPage={pm.startPage}
                endPage={pm.endPage}
                page={pm.page}
                hasNext={pm.hasNext}
                hasPrev={pm.hasPrev}
                click={clickHandler}/>
                
                </>

            }
            
        </Container>
    )
}

function PostTable({pm}) {
    return(
        <Table>
            <thead>
                <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일</th>
                <th>조회수</th>
                <th>추/비추</th>
                </tr>
            </thead>
            <tbody>
                {
                    !pm || !pm.content || pm.length === 0 ?
                    <tr>
                        <th colSpan={6}>등록된 게시글이 없습니다.</th>
                    </tr>
                    :
                        pm.content.map(post=>{
                            return(
                            <tr key={post.id}>
                                <td>{post.id}</td>
                                <td>{post.title}</td>
                                <td>{post.memberId}</td>
                                <td>{post.createdAt}</td>
                                <td>{post.viewCount}</td>
                                <td>{post.upCount}/{post.downCount}</td>
                            </tr>

                            )
                        }
                        )
                }
            </tbody>
        </Table>  
    )
    
}

export default PostList;