import os
import cv2

# Set the directories
input_dir = r'C:\Users\santh\Downloads\archive\train\angry'  # Folder containing input images
frames_dir = 'frames_folder'
annotations_dir = 'annotations_folder'

# Create the output directories if they don't exist
os.makedirs(frames_dir, exist_ok=True)
os.makedirs(annotations_dir, exist_ok=True)

# List all image files in the input directory
image_files = sorted([f for f in os.listdir(input_dir) if f.endswith(('.png', '.jpg', '.jpeg'))])

# Initialize frame count
frame_count = 0

# Iterate through the image files
for image_file in image_files:
    # Read the image
    image_path = os.path.join(input_dir, image_file)
    frame = cv2.imread(image_path)

    if frame is None:
        print(f"Error reading image: {image_file}")
        continue

   

    # Save the frame as a JPEG image in the frames directory
    frame_filename = os.path.join(frames_dir, f'frame_{frame_count:06d}.jpg')
    cv2.imwrite(frame_filename, frame)

    # Generate YOLO annotations
    # (Replace this with your own annotation logic)
    yolo_annotations = f'0 {0.5} {0.5} {1.0} {1.0}'

    # Save the YOLO annotations to a text file
    annotation_filename = os.path.join(annotations_dir, f'frame_{frame_count:06d}.txt')
    with open(annotation_filename, 'w') as f:
        f.write(yolo_annotations)

    frame_count += 1

    # Display each frame for 2 seconds
    cv2.waitKey(2000)

    # Stop after processing 20 frames
    if frame_count >= 200:
        break

# Close all windows
cv2.destroyAllWindows()

print(f'Processed {frame_count} frames.')
