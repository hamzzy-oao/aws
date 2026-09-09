import { useState } from "react"
import "./Ex5Menu.css"

const MENUS =[
    { title : "메뉴1", link : "#"},
    { title : "메뉴2", link : "#"},
    { title : "메뉴3", link : "#"}
]

function Ex5Menu() {

    const[seleteMenu,setSeleteMenu]=useState(MENUS[0].title);

    return(
        <div>
            <ul className="menu-list">
                {
                    MENUS.map(menu=>{
                        return(
                            <li className={`menu-item ${seleteMenu === menu.title ? "active" : ""}`}
                                onClick={()=>setSeleteMenu(menu.title)}
                            >
                                <a href={menu.link} className="menu-link">{menu.title}</a>
                            </li>
                        )
                    })
                }
            </ul>
            <ul className="box-list">
                {
                    MENUS.map(menu=>{
                        return(
                            <div className={`box-item ${seleteMenu === menu.title ? "active" : ""}`}>{menu.title}</div>
                        )
                    })
                }

            </ul>
        </div>
    )
    
}

export default Ex5Menu;