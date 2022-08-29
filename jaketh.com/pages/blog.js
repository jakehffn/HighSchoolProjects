import React from 'react';
import Layout from '../components/Layout';

export default function Blog() {
    return(
        <Layout>
            <div id="blog-container">


                <h1 id="title">Blog</h1>
            </div>
            
            <style jsx>{`
            #blog-container {
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