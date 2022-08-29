import React from 'react';
import Layout from '../components/Layout';

export default function Contact() {
    return(
        <Layout>
            <div id="contact-container">
                <h1 id="title">Contact</h1>

                <div id ="contact-info">
                    <ul id ="field">
                        {/* <li> Name.................................Jake Hoffman </li>
                        <li> E-mail.....................</li>
                        <li> Phone................................... </li> */}
                    </ul>
                </div>
            </div>
            
            <style jsx>{`
            #field li{
                list-style: none;
                margin: 0;
                padding-top: 14px;
            }

            #contact-info {
                width: 18%;
                margin-top: 2%;
                margin-left: 41%;

                display: inline-flex;
                color: white;
                font-family: 'Roboto', open-sans;
                font-size: 1em;
                justify-content: space-between;
            }

            #contact-container {
                position: relative;
                height: 100%;
            }

            #title {
                margin-top: 10vh;
                color: white;
                font-size: 2em;
                font-family: 'Roboto', open-sans;
                text-align: center;
            }
            `}</style>
        </Layout>   
    )
}