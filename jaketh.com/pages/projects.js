import Link from 'next/link';
import React from 'react';
import Layout from '../components/Layout';
import getAllProjects from '../components/GetProjects';

const ProjectPost = ({post}) => (
    <div id="outest">
      <Link href="/projects/[title]" as={`/projects/${post.title}` } >
        <a>
          <div id="container-outer">
              <p id="post-description">{post.title} - {post.description}</p>
            
          </div>
        </a>
      </Link>
      <style jsx>{`
      #outest {
        width: 100%;
      }

      #post-description {
        padding-left: 4px;
        position: relative;
        top: 8px;
        font-family: 'Roboto', 'Arial';
        color: white;
        z-index: 0;
        font-size: 1em;
      }

      #container-outer {
        width: 80%;
        width: calc(90% - 48px);
        margin-left: 48px;
        height: 100%;
        position: relative;
        display: flex;
        justify-content: left;
      }

    a {
      text-decoration: none;
    }
      `}</style>
    </div>
);

export default function Projects() {

    var projects = getAllProjects();

    return(
        <Layout>
            <div id="project-container">
                <h1 id="title">All Projects</h1>
                <div id="container">
                    {projects.map(post => (
                    <ProjectPost key={post.title} post={post} />
                    ))}
                </div>
            </div>
            
            <style jsx>{`
            #project-container {
                position: relative;
                height: 100%;
            }

            #title {
                color: white;
                font-size: 2em;
                font-family: 'Roboto', sans-serif;
        
                padding: 32px;
              }
            `}</style>
        </Layout>   
    )
}